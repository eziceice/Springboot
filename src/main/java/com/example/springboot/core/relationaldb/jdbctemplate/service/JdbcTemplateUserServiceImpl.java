package com.example.springboot.core.relationaldb.jdbctemplate.service;

import com.example.springboot.core.enumeration.SexEnum;
import com.example.springboot.core.relationaldb.jdbctemplate.pojo.UserJDBCTemplate;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

//@Component
public class JdbcTemplateUserServiceImpl implements JdbcTemplateUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<UserJDBCTemplate> getUserMapper() {
        RowMapper<UserJDBCTemplate> userRowMapper = (ResultSet rs, int rownum) -> {
            UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
            userJDBCTemplate.setId(rs.getLong("id"));
            userJDBCTemplate.setUserName(rs.getString("user_name"));
            int sexId = rs.getInt("sex");
            SexEnum sex = SexEnum.getEnumById(sexId);
            userJDBCTemplate.setSex(sex);
            userJDBCTemplate.setNote(rs.getString("note"));
            return userJDBCTemplate;
        };
        return userRowMapper;
    }

    @Override
    public UserJDBCTemplate getUser(Long id) {
        String sql = "select id, user_name, sex, note from t_user where id = ?";
        Object[] params = new Object[]{id};
        UserJDBCTemplate userJDBCTemplate = jdbcTemplate.queryForObject(sql, params, getUserMapper());
        return userJDBCTemplate;
    }

    @Override
    public List<UserJDBCTemplate> findUsers(String userName, String note) {
        String sql = "select id, user_name, sex, note from t_user " +
                "where user_name like concat('%', ?, '%') " +
                "and note like concat('%', ?, '%')";

        Object[] params = new Object[]{userName, note};
        List<UserJDBCTemplate> userJDBCTemplateList = jdbcTemplate.query(sql, params, getUserMapper());
        return userJDBCTemplateList;
    }

    @Override
    public int insertUser(UserJDBCTemplate userJDBCTemplate) {
        String sql = "insert into t_user (user_name, sex, note) values (?, ?, ?)";
        return jdbcTemplate.update(sql, userJDBCTemplate.getUserName(), userJDBCTemplate.getSex().getId(), userJDBCTemplate.getNote());
    }

    @Override
    public int updateUser(UserJDBCTemplate userJDBCTemplate) {
        String sql = "update t_user set user_name = ?, sex = ?, note = ?, where id = ?";
        return jdbcTemplate.update(sql, userJDBCTemplate.getUserName(), userJDBCTemplate.getSex().getId(), userJDBCTemplate.getNote(), userJDBCTemplate.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from t_user where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * Using StatementCallback to execute multiple SQL
     * @param id
     * @return
     */
    public UserJDBCTemplate getUser2(Long id)
    {
        UserJDBCTemplate result = jdbcTemplate.execute((Statement stmt) -> {
            String sql1 = "select count(*) as total from t_user where id = " + id;
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                int total = rs1.getInt("total");
                System.out.println(total);
            }


            String sql2 = "select id, user_name, sex, note from t_user where id = " + id;
            ResultSet rs2 = stmt.executeQuery(sql2);
            UserJDBCTemplate userJDBCTemplate = null;
            while (rs2.next())
            {
                int rowNum = rs2.getRow();
                userJDBCTemplate = getUserMapper().mapRow(rs2, rowNum);
            }
            return userJDBCTemplate;
        });
        return result;
    }

    /**
     * Using ConnectionCallback to execute multiple SQL
     * @param id
     * @return
     */
    public UserJDBCTemplate getUser3(Long id)
    {
        return jdbcTemplate.execute((Connection conn) -> {
            String sql1 = "select count(*) as total from t_user where id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setLong(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next())
            {
                System.out.println(rs1.getInt("total"));
            }

            String sql2 = "select id, user_name, sex, note from t_user where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setLong(1, id);
            ResultSet rs2 = ps2.executeQuery();
            UserJDBCTemplate userJDBCTemplate = null;
            while (rs2.next())
            {
                int rowNum = rs2.getRow();
                userJDBCTemplate = getUserMapper().mapRow(rs2, rowNum);
            }
            return userJDBCTemplate;
        });
    }
}

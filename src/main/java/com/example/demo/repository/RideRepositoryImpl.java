package com.example.demo.repository;

import com.example.demo.model.Ride;
import com.example.demo.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ride> getRides() {
//        Ride ride = new Ride();
//        ride.setName("Corner Canyon");
//        ride.setDuration(120);
//        List<Ride> rides = new ArrayList<>();
//        rides.add(ride);
        List<Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
        return rides;
    }

    @Override
    public Ride createRide(Ride ride) {
//        jdbcTemplate.update("insert into ride (name, duration) values (?,?) ", ride.getName(), ride.getDuration());
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement("insert into ride (name, duration) values (?,?) ", new String[]{"id"});
//            ps.setString(1, ride.getName());
//            ps.setInt(2, ride.getDuration());
//            return ps;
//        }, keyHolder);
//
//        Number id = keyHolder.getKey();
//        return getRide(id.intValue());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("name", ride.getName());
        data.put("duration", ride.getDuration());

        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("duration");

        insert.setTableName("ride");
        insert.setColumnNames(columns);
        Number id = insert.executeAndReturnKey(data);
        return getRide(id.intValue());
    }

    public Ride getRide(Integer id) {
        Ride ride = jdbcTemplate.queryForObject("select * from ride where id=?", new RideRowMapper(), id);
        return ride;
    }
}

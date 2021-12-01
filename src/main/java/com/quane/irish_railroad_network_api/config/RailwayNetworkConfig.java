package com.quane.irish_railroad_network_api.config;

import com.quane.irish_railroad_network_api.model.RailwayNetwork;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Configuration
@AllArgsConstructor
public class RailwayNetworkConfig {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public RailwayNetwork createRailwayNetwork() {

        List<List<Boolean>> tempMatrix;
        HashMap<Integer, String> tempStations = new HashMap<>();
        RailwayNetwork railwayNetwork = new RailwayNetwork();

        String sql = "SELECT * FROM irishrailroadnetwork.`irishrailnetwork-stations`";
        jdbcTemplate.query(sql, (rs, rowNum) -> tempStations.put(rs.getInt(1), rs.getString(2)));
        railwayNetwork.setStations(tempStations);


        sql = "SELECT * FROM irishrailroadnetwork.`irishrailnetwork-adjacencymatrix`";
        tempMatrix = jdbcTemplate.query(
                    sql,
                    (rs, rowNum) ->
                            new ArrayList<Boolean>(
                                    Arrays.asList(rs.getBoolean(1),
                                            rs.getBoolean(2),
                                            rs.getBoolean(3),
                                            rs.getBoolean(4),
                                            rs.getBoolean(5),
                                            rs.getBoolean(6),
                                            rs.getBoolean(7),
                                            rs.getBoolean(8),
                                            rs.getBoolean(9),
                                            rs.getBoolean(10),
                                            rs.getBoolean(11),
                                            rs.getBoolean(12),
                                            rs.getBoolean(13),
                                            rs.getBoolean(14),
                                            rs.getBoolean(15),
                                            rs.getBoolean(16),
                                            rs.getBoolean(17),
                                            rs.getBoolean(18),
                                            rs.getBoolean(19),
                                            rs.getBoolean(20),
                                            rs.getBoolean(21),
                                            rs.getBoolean(22),
                                            rs.getBoolean(23),
                                            rs.getBoolean(24),
                                            rs.getBoolean(25),
                                            rs.getBoolean(26),
                                            rs.getBoolean(27),
                                            rs.getBoolean(28),
                                            rs.getBoolean(29),
                                            rs.getBoolean(30),
                                            rs.getBoolean(31),
                                            rs.getBoolean(32),
                                            rs.getBoolean(33),
                                            rs.getBoolean(34),
                                            rs.getBoolean(35),
                                            rs.getBoolean(36),
                                            rs.getBoolean(37),
                                            rs.getBoolean(38),
                                            rs.getBoolean(39),
                                            rs.getBoolean(40),
                                            rs.getBoolean(41),
                                            rs.getBoolean(42),
                                            rs.getBoolean(43),
                                            rs.getBoolean(44),
                                            rs.getBoolean(45),
                                            rs.getBoolean(46),
                                            rs.getBoolean(47),
                                            rs.getBoolean(48),
                                            rs.getBoolean(49),
                                            rs.getBoolean(50),
                                            rs.getBoolean(51),
                                            rs.getBoolean(52),
                                            rs.getBoolean(53),
                                            rs.getBoolean(54),
                                            rs.getBoolean(55),
                                            rs.getBoolean(56),
                                            rs.getBoolean(57),
                                            rs.getBoolean(58),
                                            rs.getBoolean(59),
                                            rs.getBoolean(60),
                                            rs.getBoolean(61),
                                            rs.getBoolean(62),
                                            rs.getBoolean(63),
                                            rs.getBoolean(64),
                                            rs.getBoolean(65),
                                            rs.getBoolean(66),
                                            rs.getBoolean(67),
                                            rs.getBoolean(68),
                                            rs.getBoolean(69),
                                            rs.getBoolean(70),
                                            rs.getBoolean(71),
                                            rs.getBoolean(72),
                                            rs.getBoolean(73),
                                            rs.getBoolean(74),
                                            rs.getBoolean(75),
                                            rs.getBoolean(76),
                                            rs.getBoolean(77),
                                            rs.getBoolean(78),
                                            rs.getBoolean(79),
                                            rs.getBoolean(80),
                                            rs.getBoolean(81),
                                            rs.getBoolean(82),
                                            rs.getBoolean(83),
                                            rs.getBoolean(84),
                                            rs.getBoolean(85),
                                            rs.getBoolean(86),
                                            rs.getBoolean(87),
                                            rs.getBoolean(88),
                                            rs.getBoolean(89),
                                            rs.getBoolean(90),
                                            rs.getBoolean(91),
                                            rs.getBoolean(92),
                                            rs.getBoolean(93),
                                            rs.getBoolean(94),
                                            rs.getBoolean(95),
                                            rs.getBoolean(96),
                                            rs.getBoolean(97),
                                            rs.getBoolean(98),
                                            rs.getBoolean(99),
                                            rs.getBoolean(100),
                                            rs.getBoolean(101),
                                            rs.getBoolean(102)
                                            )));

        railwayNetwork.setAdjacencyMatrix(tempMatrix);

        return railwayNetwork;
    }

//    public int count() {
//
//        String sql = "SELECT COUNT(*) FROM railway_network.`irishrailnetwork-adjacencymatrix`";
//
//        return jdbcTemplate.queryForObject(sql, Integer.class);
//
//    }

}

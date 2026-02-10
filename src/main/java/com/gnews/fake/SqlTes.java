package com.gnews.fake;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SqlTes {

    private final JdbcTemplate jdbcTemplate;

    public SqlTes(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<News> findByTitle(String userInput) {
        String query = "SELECT * FROM news WHERE title = '" + userInput + "'";
        return jdbcTemplate.query(query, new NewsRowMapper());
    }

    public static class News {
        private String title;
        private String content;

        // Getters and setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    private static class NewsRowMapper implements RowMapper<News> {
        @Override
        public News mapRow(ResultSet rs, int rowNum) throws SQLException {
            News news = new News();
            news.setTitle(rs.getString("title"));
            news.setContent(rs.getString("content"));
            return news;
        }
    }
}

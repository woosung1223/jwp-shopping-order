package cart.persistence.repository;

import cart.entity.Member;
import cart.application.repository.MemberRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberPersistenceAdapter implements MemberRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MemberPersistenceAdapter(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT * FROM member WHERE email = :email";
        SqlParameterSource namedParameters = new MapSqlParameterSource("email", email);

        return namedParameterJdbcTemplate.query(sql, namedParameters, (rs, rowNum) -> new Member(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("point"))
        ).stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM member";

        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> new Member(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("point")
        ));
    }

    @Override
    public void update(Member updatedMember) {
        String sql = "UPDATE member SET email = :email, password = :password, point = :point WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("email", updatedMember.getEmail())
                .addValue("password", updatedMember.getPassword())
                .addValue("point", updatedMember.getPoint())
                .addValue("id", updatedMember.getId());

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}

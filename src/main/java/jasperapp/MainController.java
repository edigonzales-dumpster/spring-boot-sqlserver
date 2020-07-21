package jasperapp;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/fubar", method = RequestMethod.GET)
	public List<String> foo() {
        List<String> result = jdbcTemplate.query(
                "SELECT TOP 10 OBJECT_ID, ART_DER_FUNDSTELLE FROM dbo.REP_VIEW_FUNDSTELLEN WHERE ART_DER_FUNDSTELLE IS NOT NULL",
                (rs, rowNum) -> new String(rs.getString("ART_DER_FUNDSTELLE"))
        );
        return result;
	}
}

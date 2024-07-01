package ${cfg.criteria};

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
* <p>
* ${table.comment!} QueryCriteria
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
public class ${entity}QueryCriteria implements Serializable {
    private String blurry;

    private List<Timestamp> createTime;

    private Integer pageNum;

    private Integer pageSize;
}
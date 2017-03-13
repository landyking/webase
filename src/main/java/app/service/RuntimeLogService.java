package app.service;

import app.model.RuntimeLog;
import app.util.*;
import org.javalite.activejdbc.LazyList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/14 19:48
 * note:
 */
@Service
public class RuntimeLogService {
    public List<Map<String, Object>> list(PageRequest pr, Map<String, String> params) {
        try (ActiveJdbcMojo mojo = new ActiveJdbcMojo()) {
            pr.clearFilters();
            String logDesc = params.get("log_desc");
            String startDateStr = params.get("start_date");
            String endDateStr = params.get("end_date");

            if (Texts.hasText(logDesc)) {
                pr.addFilter("log_desc like ?", "%" + logDesc + "%");
            }
            if (Texts.hasText(startDateStr)) {
                Date startDate = FreeworkDateEditor.parseDate(startDateStr);
                if (startDate != null) {
                    pr.addFilter("start_time>=?", DateTool.toDateTime(startDate));
                }
            }
            if (Texts.hasText(endDateStr)) {
                Date endDate = FreeworkDateEditor.parseDate(endDateStr);
                if (endDate != null) {
                    pr.addFilter("start_time<?", DateTool.toDateTime(endDate));
                }
            }
            if (!pr.needSort()) {
                pr.makeSort("log_id desc", "");
            }
            Long totalCount = RuntimeLog.count(pr.filtersToSql(), pr.filtersToArray());
            pr.computePageParam(totalCount.intValue());

            LazyList<RuntimeLog> pageList = RuntimeLog.where(pr.filtersToSql(), pr.filtersToArray())
                    .orderBy(pr.sortToSql())
                    .offset(pr.getStart())
                    .limit(pr.getNumPerPage());
            List<Map<String, Object>> dataList = new ArrayList<>(pageList.size());
            for (RuntimeLog one : pageList) {
                Map<String, Object> tmp = one.toMap();
                dataList.add(tmp);
            }
            return dataList;
        }
    }
}

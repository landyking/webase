package app.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *  <br/>
 *
 * @author: landy
 * @date: 2017/2/13 17:15
 * note:
 */
public class PageRequest {
    private String orderField;
    private String orderDirection;
    private int numPerPage;
    private int page;
    private final List<Tuple<String, Object>> filters = new ArrayList<>(10);
    private int start;
    private int totalCount;


    private PageRequest() {
    }


    public static PageRequest parsePageRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String orderField = request.getParameter("_order");
        String orderDirection = request.getParameter("_sort");
        String numPerPageString = request.getParameter("numPerPage");
        String pageString = request.getParameter("page");
        PageRequest pr = new PageRequest();
        if (Texts.hasText(orderField)) {
            pr.setOrderField(orderField.trim());
        }
        if (Texts.hasText(orderDirection)) {
            pr.setOrderDirection(orderDirection.trim());
        }
        int numPerPage = 20;
        if (Texts.hasText(numPerPageString)) {
            try {
                numPerPage = Integer.parseInt(numPerPageString.trim());
            } catch (Exception e) {
            }
        }
        if (numPerPage < 1) {
            numPerPage = 1;
        }
        pr.setNumPerPage(numPerPage);
        int page = 1;
        if (Texts.hasText(pageString)) {
            try {
                page = Integer.parseInt(pageString.trim());
            } catch (Exception e) {
            }
        }
        if (page < 1) {
            page = 1;
        }
        pr.setPage(page);
        return pr;
    }

    public String getOrderField() {
        return orderField;
    }

    public void clearFilters() {
        filters.clear();
    }

    public void addFilter(String name, Object value) {
        filters.add(Tuple.newOne(name, value));
    }

    /*public List<Tuple<String, Object>> getFilters() {
        return filters;
    }*/

    public String filtersToSql() {
        if (filters.isEmpty()) {
            return " 1=1 ";
        }
        StringBuilder sb = new StringBuilder();
        boolean mark = true;
        for (Tuple<String, Object> one : filters) {
            sb.append(" ");
            if (mark) {
                sb.append(one.getFirst());
                mark = false;
            } else {
                sb.append("and ");
                sb.append(one.getFirst());
            }
        }
        if (!mark) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Object> filtersToList() {
        List<Object> result = new ArrayList<>(filters.size());
        for (Tuple<String, Object> one : filters) {
            if (one.getSecond() != null) {
                result.add(one.getSecond());
            }
        }
        return result;
    }

    public Object[] filtersToArray() {
        List<Object> tmp = filtersToList();
        return tmp.toArray(new Object[tmp.size()]);
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean needSort() {
        return Texts.hasText(orderField) && Texts.hasText(orderDirection);
    }

    public void makeSort(String orderField, String orderDirection) {
        this.orderField = orderField;
        this.orderDirection = orderDirection;
    }

    public void computePageParam(int totalCount) {
        int totalPage = (totalCount / numPerPage) + ((totalCount % numPerPage == 0) ? 0 : 1);
        if (totalPage < this.page) {
            this.page = totalPage;
        }
        if (this.page < 1) {
            this.page = 1;
        }
        this.start = (this.page - 1) * numPerPage;
        this.totalCount = totalCount;
    }

    public String sortToSql() {
        return this.orderField + " " + this.orderDirection;
    }
}

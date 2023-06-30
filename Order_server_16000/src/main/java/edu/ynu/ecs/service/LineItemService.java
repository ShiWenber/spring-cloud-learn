package edu.ynu.ecs.service;

import edu.ynu.ecs.dao.LineItemDao;
import edu.ynu.ecs.entities.LineItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class LineItemService extends AbstractTypedService<LineItem, String> {
    @Resource
    LineItemDao lineItemDao;

    public LineItemService(LineItemDao dao) {
        this.dataContext = dao;
        this.lineItemDao = dao;
    }

    public List<LineItem> queryAll() {
        List<LineItem> lst = null;
        try {
            lst = ((LineItemDao) this.dataContext).findAll();
            log.info("find lineItem: " + lst.size());
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return lst;
    }

    public Page<LineItem> queryLineItemByPage(Pageable pageable) {
        return lineItemDao.queryPage(pageable, null);
    }
}

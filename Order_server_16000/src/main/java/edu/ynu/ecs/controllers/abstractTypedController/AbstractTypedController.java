package edu.ynu.ecs.controllers.abstractTypedController;

import edu.ynu.ecs.dao.specification.AbstractQueryCondition;
import edu.ynu.ecs.entities.AbstractDomainEntity;
import edu.ynu.ecs.service.AbstractTypedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//将这些接口放置到group test-controller 中并防止在子类中显示
//@Tag(name = "test-controller", description = "测试控制器")
public class AbstractTypedController<T extends AbstractDomainEntity, IdType> {

    /**
     * 学生服务
     */
//     @Resource
    protected AbstractTypedService<T, IdType> svcContext;

    //#region 关联的数据实体

    @Operation(summary = "查询 单个实体",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    @GetMapping("/{id}")
    public T queryById(@PathVariable IdType id) {
        return svcContext.getByIdNotNull(id);
    }

	/**
	 * Operation 中的 security 用于指定当前接口的安全策略，如果不指定，则使用全局的安全策略
	 * @return
	 */
    @GetMapping("/all")
    @Operation(summary = "查询 全部实体",  security = {
		@SecurityRequirement(name = "Authorization")
	})
    public List<T> findAll() {
        return svcContext.findAll();
    }

    @GetMapping("/by")
    @Operation(summary = "查询 符合条件的所有实体",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    public List<T> findBy(@ModelAttribute AbstractQueryCondition condition, @Nullable Sort sort) {
        return svcContext.findBy(condition, sort);
    }

    /**
     * @param pageable 分页
     * @return
     */
    @Operation(summary = "查询 分页默认按照创建时间排序",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<T> pageQuery(Pageable pageable) {
        return svcContext.pageQuery(pageable);
    }

    /**
     * 根据条件分页查询对象
     *
     * @param condition 查询条件
     * @param pageable  分页
     * @return
     */
    public Page<T> pageQueryBy(@ModelAttribute AbstractQueryCondition condition, Pageable pageable) {
        return svcContext.pageQuery(pageable, condition);
    }

    //#endregion

    //#region 关联的数据实体基本操作

    @PostMapping
    @Operation(summary = "创建 数据实体", security = {
		@SecurityRequirement(name = "Authorization")
	})
    public T create(@RequestBody T item) {
        return svcContext.create(item);
    }

    @PutMapping
    @Operation(summary = "修改 数据实体",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    public T update(@RequestBody T item) {
        return svcContext.update(item);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除 数据实体",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    public ResponseEntity<Void> delete(@PathVariable IdType id) {
        svcContext.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量新建",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    public ResponseEntity<Void> batchCreate(@RequestBody List<T> items) {
        var res = svcContext.batchCreate(items);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/batch")
    @Operation(summary = "批量更新",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    public ResponseEntity<Void> batchUpdate(@RequestBody List<T> items) {
        var res = svcContext.batchUpdate(items);
        var ids = res.stream().map(r -> r.getEntityId()).toArray();

        log.debug("items: {}", ids.length);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除",  security = {
            @SecurityRequirement(name = "Authorization")
    })
    public ResponseEntity<Integer> batchDelete(@RequestParam List<IdType> ids) {
        var res = svcContext.deleteAll(ids);
        return new ResponseEntity<Integer>(res, HttpStatus.OK);
    }

    //#endregion

}

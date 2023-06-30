package edu.ynu.ecs.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;



@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public   class AbstractDomainEntity
        implements Cloneable, Serializable {
    /**
     * 统一物理主键 uuid 版本，需要提供给子类访问所以用protected
     */
    @Size(max = 32)
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", nullable = false, length = 32)
    @Schema(description = "id" )
    protected String id;
    /**
     * 创建日期
     */
    @Schema(description = "实体创建时间")
    @CreatedDate
    protected Date createdDate;

    /**
     * 最后更新日期 Timestamp
     */
    @Schema(description = "实体最后更新时间")
    @LastModifiedDate
    private Date lastModifiedDate;

    /**
     * 获取实体的id
     *
     * @return
     */
    public Object getEntityId() {
        return id;
    }

    /**
     * 克隆当前对象
     *
     * @return AbstractDomainEntity
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

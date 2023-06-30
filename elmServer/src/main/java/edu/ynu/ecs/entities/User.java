package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
@Inheritance
// 必须指明类型和type属性的类型一致，否则会报错,TODO:这里的type在建立实体的时候会给一个长串值而非预想的012
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public class User extends AbstractDomainEntity implements UserDetails {
//	user的属性都需要为protect，否则子类无法访问，导致查询失败


	/**
	 * 统一物理主键 uuid 版本，需要提供给子类访问所以用protected
	 */
	@Size(max = 32)
	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
	@Column(name = "id", nullable = false, length = 32)
	@Schema(description = "id")
	protected String id;

    @Size(max = 20)
    @NotNull
    @Column(name = "password", nullable = false, length = 20)
    @Schema(description = "顾客密码")
    protected String password;

    @Size(max = 20)
    @NotNull
    @Column(name = "username", nullable = false, length = 20)
    @Schema(description = "顾客名")
    protected String username;

	@Column(updatable = false, insertable = false)
	@Schema(description = "顾客类型 1 customer, 2 business")
	protected Integer type;

    @NotNull
    @Column(name = "sex", nullable = false)
    @Schema(description = "顾客性别")
    protected Integer sex;

	@Size(max = 30)
	@Column(name = "email", nullable = true, length = 30)
	@Schema(description = "邮箱")
	protected String email;

	@Size(max = 20)
	@Column(name = "tel", nullable = true, length = 20)
	@Schema(description = "电话")
	protected String tel;

    @Schema(description = "顾客是否没有过期")
    protected boolean accountNonExpired = true;

    @Schema(description = "顾客是否没有被锁定")
    protected boolean accountNonLocked = true;

    @Schema(description = "顾客凭证是否没有过期")
    protected boolean credentialsNonExpired = true;

    @Schema(description = "顾客是否可用")
    protected boolean enabled = true;


	@JsonIgnore
	@Transient
	// 不持久化到数据库，也不显示在restful接口的属性
	protected String secret;

	@Transient
	protected String accessToken;


	/**
	 * Returns the authorities granted to the user. Cannot return <code>null</code>.
	 *
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */
	@Transient
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
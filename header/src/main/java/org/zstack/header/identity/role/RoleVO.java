package org.zstack.header.identity.role;

import org.zstack.header.identity.HasAccountResourceRef;
import org.zstack.header.identity.OwnedByAccount;
import org.zstack.header.vo.ResourceVO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@HasAccountResourceRef
public class RoleVO extends ResourceVO implements OwnedByAccount {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Timestamp createDate;
    @Column
    private Timestamp lastOpDate;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleType type;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleUuid", insertable = false, updatable = false)
    private Set<RolePolicyStatementVO> statements = new HashSet<>();

    @Transient
    private String accountUuid;

    public RoleVO copy() {
        RoleVO vo = new RoleVO();
        vo.name = name;
        vo.uuid = uuid();
        vo.description = description;
        vo.type = type;
        return vo;
    }

    @PreUpdate
    private void preUpdate() {
        lastOpDate = null;
    }

    @Override
    public String getAccountUuid() {
        return accountUuid;
    }

    @Override
    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public Set<RolePolicyStatementVO> getStatements() {
        return statements;
    }

    public void setStatements(Set<RolePolicyStatementVO> statements) {
        this.statements = statements;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastOpDate() {
        return lastOpDate;
    }

    public void setLastOpDate(Timestamp lastOpDate) {
        this.lastOpDate = lastOpDate;
    }
}

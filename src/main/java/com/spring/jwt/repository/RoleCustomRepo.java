package com.spring.jwt.repository;

import com.spring.jwt.entity.Role;
import com.spring.jwt.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoleCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Role> getRole(User user){
        StringBuilder sql = new StringBuilder()
                .append("select r.role_name as name from users u join user_role ur on u.user_id = ur.user_id join roles r on r.role_id = ur.role_id");

        if(user.getUsername()!=null){
            sql.append(" where username =:username");
        }
        NativeQuery<Role> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        if(user.getUsername()!=null){
            query.setParameter("username", user.getUsername());
        }
        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }
}

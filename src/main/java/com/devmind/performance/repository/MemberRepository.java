
package com.devmind.performance.repository;

import com.devmind.performance.model.member.Member;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link Member}
 */
public interface MemberRepository extends CrudRepository<Member, Long> {

    Member findByEmail(String email);
}

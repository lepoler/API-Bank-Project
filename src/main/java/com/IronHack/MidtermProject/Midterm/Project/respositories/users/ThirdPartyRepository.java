package com.IronHack.MidtermProject.Midterm.Project.respositories.users;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {


    Optional<ThirdParty> findByHashKey(String hashKey);


}

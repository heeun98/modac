package com.project.modac.repository;

import com.project.modac.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<HashTag, Long> {

    Optional<HashTag> findByName(String hashtag);
}

package com.pragma.bootcamp.infraestructure.output.jpa.repository;

import com.pragma.bootcamp.domain.model.BootcampWithCount;
import com.pragma.bootcamp.infraestructure.output.jpa.entity.BootcampEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface IBootcampRepository  extends ReactiveCrudRepository<BootcampEntity,Long> {
    // NAME ASC
    @Query("""
      SELECT b.id,
             b.name,
             b.description,
             b.date,
             b.duration,
             COUNT(bc.capability_id) AS capability_count
        FROM bootcamps b
        LEFT JOIN bootcamp_capability bc
          ON b.id = bc.bootcamp_id
       GROUP BY b.id, b.name, b.description, b.date, b.duration
       ORDER BY b.name ASC
       LIMIT :limit OFFSET :offset
    """)
    Flux<BootcampWithCount> findPageOrderByNameAsc(
            @Param("limit")  int limit,
            @Param("offset") long offset
    );

    // NAME DESC
    @Query("""
      SELECT b.id,
             b.name,
             b.description,
             b.date,
             b.duration,
             COUNT(bc.capability_id) AS capability_count
        FROM bootcamps b
        LEFT JOIN bootcamp_capability bc
          ON b.id = bc.bootcamp_id
       GROUP BY b.id, b.name, b.description, b.date, b.duration
       ORDER BY b.name DESC
       LIMIT :limit OFFSET :offset
    """)
    Flux<BootcampWithCount> findPageOrderByNameDesc(
            @Param("limit")  int limit,
            @Param("offset") long offset
    );

    // CAPABILITY COUNT ASC
    @Query("""
      SELECT b.id,
             b.name,
             b.description,
             b.date,
             b.duration,
             COUNT(bc.capability_id) AS capability_count
        FROM bootcamps b
        LEFT JOIN bootcamp_capability bc
          ON b.id = bc.bootcamp_id
       GROUP BY b.id, b.name, b.description, b.date, b.duration
       ORDER BY capability_count ASC
       LIMIT :limit OFFSET :offset
    """)
    Flux<BootcampWithCount> findPageOrderByCountAsc(
            @Param("limit")  int limit,
            @Param("offset") long offset
    );

    // CAPABILITY COUNT DESC
    @Query("""
      SELECT b.id,
             b.name,
             b.description,
             b.date,
             b.duration,
             COUNT(bc.capability_id) AS capability_count
        FROM bootcamps b
        LEFT JOIN bootcamp_capability bc
          ON b.id = bc.bootcamp_id
       GROUP BY b.id, b.name, b.description, b.date, b.duration
       ORDER BY capability_count DESC
       LIMIT :limit OFFSET :offset
    """)
    Flux<BootcampWithCount> findPageOrderByCountDesc(
            @Param("limit")  int limit,
            @Param("offset") long offset
    );
}

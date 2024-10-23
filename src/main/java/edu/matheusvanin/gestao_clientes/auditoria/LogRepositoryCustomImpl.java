package edu.matheusvanin.gestao_clientes.auditoria;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.TIMESTAMP;

@Repository
@RequiredArgsConstructor
public class LogRepositoryCustomImpl implements LogRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    private void validateFilter(Query query, String field, Object value) {
        if (value != null) {
            query.addCriteria(Criteria.where(field).is(value));
        }
    }


    @Override
    public Page<Log> findLogsByFilters(Pageable pageable, String method, String endpoint,
                                       Integer responseStatus, LocalDateTime from, LocalDateTime to) {
        Query query = new Query();
        validateFilter(query, "method", method);
        validateFilter(query, "endpoint", endpoint);
        validateFilter(query, "responseStatus", responseStatus);
        if (from != null && to != null) {
            query.addCriteria(Criteria.where(TIMESTAMP).gte(from).lte(to));
        } else if (from != null) {
            query.addCriteria(Criteria.where(TIMESTAMP).gte(from));
        } else if (to != null) {
            query.addCriteria(Criteria.where(TIMESTAMP).lte(to));
        }
        query.with(pageable);

        List<Log> logs = mongoTemplate.find(query, Log.class);
        long total = mongoTemplate.count(query.skip(-1).limit(-1), Log.class);

        return new PageImpl<>(logs, pageable, total);
    }
}

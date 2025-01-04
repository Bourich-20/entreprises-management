package com.entreprises_management.project_service.repository;

import com.entreprises_management.project_service.entitie.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

package com.borelanjo.jobservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.borelanjo.jobservice.domain.model.Status.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_job", schema = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private UUID code;

    @Column
    private String type;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    public boolean isRunning() {
        return RUNNING.equals(this.getStatus());
    }

    public boolean isWaiting() {
        return WAITING.equals(this.getStatus());
    }

    public boolean isSuccess() {
        return SUCCESS.equals(this.getStatus());
    }

    public boolean isError() {
        return ERROR.equals(this.getStatus());
    }

    public boolean isDead() {
        return DEAD.equals(this.getStatus());
    }

    public boolean isEnded() {
        return isSuccess() || isError() || isDead();
    }

}

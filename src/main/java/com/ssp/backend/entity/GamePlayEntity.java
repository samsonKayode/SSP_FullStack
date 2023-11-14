package com.ssp.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssp.backend.enums.GameMove;
import com.ssp.backend.enums.GamePlayResult;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Table(name = "game_play")
public class GamePlayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "playerMove")
    private GameMove playerMove;

    @Column(name = "computerMove")
    private GameMove computerMove;

    @Column(name = "winner")
    private GamePlayResult winner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserEntity userEntity;
}

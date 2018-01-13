/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michaela Bocanova
 */
public class MatchCreateDto {
    
    private LocalDateTime dateAndTime;
    private LocationDto location;
    @Min(0)
    private int teamHomeGoalsScored;
    @Min(0)
    private int teamAwayGoalsScored;
    @Min(0)
    private int teamHomeGoalsScoredHalf;
    @Min(0)
    private int teamAwayGoalsScoredHalf;
    @NotNull
    private Long teamHomeId;
    @NotNull
    private Long teamAwayId;
    /*@NotNull
    @JsonIgnore
    private TeamDto teamHome;
    
    @NotNull
    @JsonIgnore
    private TeamDto teamAway;

    @JsonIgnore
    private List<PlayerResultDto> playerResults = new ArrayList<>();*/
    
    public MatchCreateDto() {
    }       

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public int getTeamHomeGoalsScored() {
        return teamHomeGoalsScored;
    }

    public void setTeamHomeGoalsScored(int teamHomeGoalsScored) {
        this.teamHomeGoalsScored = teamHomeGoalsScored;
    }

    public int getTeamAwayGoalsScored() {
        return teamAwayGoalsScored;
    }

    public void setTeamAwayGoalsScored(int teamAwayGoalsScored) {
        this.teamAwayGoalsScored = teamAwayGoalsScored;
    }

    public int getTeamHomeGoalsScoredHalf() {
        return teamHomeGoalsScoredHalf;
    }

    public void setTeamHomeGoalsScoredHalf(int teamHomeGoalsScoredHalf) {
        this.teamHomeGoalsScoredHalf = teamHomeGoalsScoredHalf;
    }

    public int getTeamAwayGoalsScoredHalf() {
        return teamAwayGoalsScoredHalf;
    }

    public void setTeamAwayGoalsScoredHalf(int teamAwayGoalsScoredHalf) {
        this.teamAwayGoalsScoredHalf = teamAwayGoalsScoredHalf;
    }
    

    public Long getTeamHomeId() {
        return teamHomeId;
    }

    public void setTeamHomeId(Long teamHomeId) {
        this.teamHomeId = teamHomeId;
    }

    public Long getTeamAwayId() {
        return teamAwayId;
    }

    public void setTeamAwayId(Long teamAwayId) {
        this.teamAwayId = teamAwayId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.teamHomeId);
        hash = 29 * hash + Objects.hashCode(this.teamAwayId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MatchCreateDto other = (MatchCreateDto) obj;
        if (this.teamHomeGoalsScored != other.teamHomeGoalsScored) {
            return false;
        }
        if (this.teamAwayGoalsScored != other.teamAwayGoalsScored) {
            return false;
        }
        if (this.teamHomeGoalsScoredHalf != other.teamHomeGoalsScoredHalf) {
            return false;
        }
        if (this.teamAwayGoalsScoredHalf != other.teamAwayGoalsScoredHalf) {
            return false;
        }
        if (!Objects.equals(this.dateAndTime, other.dateAndTime)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.teamHomeId, other.teamHomeId)) {
            return false;
        }
        if (!Objects.equals(this.teamAwayId, other.teamAwayId)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "MatchCreateDto{" + "dateAndTime=" + dateAndTime + ", location=" + location + ", teamHomeGoalsScored=" + teamHomeGoalsScored + ", teamAwayGoalsScored=" + teamAwayGoalsScored + ", teamHomeGoalsScoredHalf=" + teamHomeGoalsScoredHalf + ", teamAwayGoalsScoredHalf=" + teamAwayGoalsScoredHalf + ", teamHomeId=" + teamHomeId + ", teamAwayId=" + teamAwayId + '}';
    }
      
    
}

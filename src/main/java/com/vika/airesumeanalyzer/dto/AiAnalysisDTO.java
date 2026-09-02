package com.vika.airesumeanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AiAnalysisDTO {

    private String keySkills;

    private String strengths;

    private String weaknesses;

    private String recommendedSkills;

    private String improvementSuggestions;

    private String overallAssessment;

    @JsonProperty("ATSScore")
    private String atsScore;

    public AiAnalysisDTO() {
    }

    public AiAnalysisDTO(String keySkills,
                         String strengths,
                         String weaknesses,
                         String recommendedSkills,
                         String improvementSuggestions,
                         String overallAssessment,
                         String atsScore) {

        this.keySkills = keySkills;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.recommendedSkills = recommendedSkills;
        this.improvementSuggestions = improvementSuggestions;
        this.overallAssessment = overallAssessment;
        this.atsScore = atsScore;
    }

    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getRecommendedSkills() {
        return recommendedSkills;
    }

    public void setRecommendedSkills(String recommendedSkills) {
        this.recommendedSkills = recommendedSkills;
    }

    public String getImprovementSuggestions() {
        return improvementSuggestions;
    }

    public void setImprovementSuggestions(String improvementSuggestions) {
        this.improvementSuggestions = improvementSuggestions;
    }

    public String getOverallAssessment() {
        return overallAssessment;
    }

    public void setOverallAssessment(String overallAssessment) {
        this.overallAssessment = overallAssessment;
    }

    @JsonProperty("ATSScore")
    public String getAtsScore() {
        return atsScore;
    }

    @JsonProperty("ATSScore")
    public void setAtsScore(String atsScore) {
        this.atsScore = atsScore;
    }
}


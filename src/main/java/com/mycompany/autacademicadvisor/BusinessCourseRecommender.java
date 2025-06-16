package com.mycompany.autacademicadvisor;

/**
 *
 * @author Grady
 */

import javax.swing.*;

public class BusinessCourseRecommender extends BaseCourseRecommender {

    @Override
    public String[] getAvailableMajors() {
        return new String[]{
            "Accounting",
            "Economics",
            "Finance",
            "Human Resource Management",
            "Information Systems",
            "International Business",
            "Management and Leadership",
            "Marketing"
        };
    }

    @Override
    public void recommendCourses(String major, String year, JTextArea textArea) {
        textArea.setText("");
        textArea.append("Recommended Courses for " + major + " - Year " + year + ":\n\n");
        
        switch (major) {
            case "Accounting" -> recommendAccountingCourses(year, textArea);
            case "Economics" -> recommendEconomicsCourses(year, textArea);
            case "Finance" -> recommendFinanceCourses(year, textArea);
            case "Human Resource Management" -> recommendHumanResourceManagementCourses(year, textArea);
            case "Information Systems" -> recommendInformationSystemsCourses(year, textArea);
            case "International Business" -> recommendInternationalBusinessCourses(year, textArea);
            case "Management and Leadership" -> recommendManagementAndLeadershipCourses(year, textArea);
            case "Marketing" -> recommendMarketingCourses(year, textArea);
            default -> textArea.append("Major not recognized\n");
        }
    }

    private void recommendAccountingCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Accounting Essentials (ACCT502)",
                    "Financial Accounting (ACCT602)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Accounting Systems and Analytics (ACCT503)",
                    "Business Economics and Data Analysis (ECON505)",
                    "Accounting Legal and Regulatory (ACCT605)",
                    "Financial Management for Accountants (ACCT606)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Advanced Financial Accounting (ACCT702)",
                    "Advanced Management Accounting (ACCT703)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Accounting\n");
        }
    }

    private void recommendEconomicsCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Principles of Microeconomics (ECON520)",
                    "Principles of Macroeconomics (ECON521)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Microeconomics: Choice and Welfare (ECON620)",
                    "Macroeconomics: Models, Data and Policy (ECON622)",
                    "Introduction to Econometrics (ECON622)",
                    "Microeconomics: Competition, Risk and Strategy (ECON720)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Applied Macroeconomics (ECON721)",
                    "Applied Econometrics: Causal Inference for Social Impact (ECON722)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Economics\n");
        }
    }

    // Similar methods for other majors (Finance, HR Management, etc.)
    // ... (remaining methods follow the same pattern)
    
    private void recommendFinanceCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Introduction to Finance (FINA501)",
                    "Financial Analysis (FINA503)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Corporate Finance (FINA601)",
                    "Investment and Portfolio Analysis (FINA602)",
                    "Social Impact for Finance (FINA608)",
                    "International Corporate Finance (FINA701)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Financial Risk Management (FINA702)",
                    "Financial Institutions and Markets (FINA706)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Finance\n");
        }
    }

    private void recommendHumanResourceManagementCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Managing and Organising (MGMT501)",
                    "Employment Relations (EMPL601)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Human Resource Management (HRMG601)",
                    "Learning and Development (HRMG602)",
                    "Workplace Health and Safety (EMPL702)",
                    "The Dynamics of Employment Regulations (EMPL704)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Diversity and Inclusion for Social Impact (HRMG701)",
                    "Global Mobility (HRMG703)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for HR Management\n");
        }
    }

    private void recommendInformationSystemsCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Business Information Systems (BSYS501)",
                    "Business Process Management (BSYS601)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Business Data Management (BSYS602)",
                    "Project Management (BSYS603)",
                    "Enterprise Information Systems (BSYS701)",
                    "Cyber-security and Risk Management (BSYS702)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Digital Transformation and Social Impact (BSYS705)",
                    "Designing Systems for Contemporary Enterprises (BSYS706)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Information Systems\n");
        }
    }

    private void recommendInternationalBusinessCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Business and Strategy in a Changing World (INTB501)",
                    "Dynamic Environments (INTB603)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "International Business Management (INTB604)",
                    "Business Strategy (INTB605)",
                    "Global Social Impact (INTB706)",
                    "Strategy in Uncertain Times (INTB707)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Innovation and Technology Strategy (INTB708)",
                    "Strategic Design (INTB709)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for International Business\n");
        }
    }

    private void recommendManagementAndLeadershipCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Managing and Organising (MGMT501)",
                    "Human Resource Management (HRMG601)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Business Strategy (INTB605)",
                    "Small and Medium Enterprise Management (MGMT602)",
                    "Leadership for Change (MGMT603)",
                    "Management for Social Impact (MGMT704)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Stress and Resilience at Work (MGMT705)",
                    "Organising for the Future of Work (MGMT708)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Management\n");
        }
    }

    private void recommendMarketingCourses(String year, JTextArea textArea) {
        switch (year) {
            case "1" -> {
                String[] courses = {
                    "Consumer and Organisational Behaviour (BUSS505)",
                    "Financial Decision Making and Information Systems (BUSS507)",
                    "Economics and International Business (BUSS508)",
                    "Ethics, Responsibility and Sustainability (BUSS509)",
                    "Marketing Insights (MKTG501)",
                    "Marketing Research (MKTG601)",
                    "2 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "2" -> {
                String[] courses = {
                    "Marketing Relationships (MKTG602)",
                    "Consumer Behaviour (MKTG603)",
                    "Strategic Marketing (MKTG701)",
                    "Integrated Marketing Communications (MKTG702)",
                    "4 Chosen Minor Courses"
                };
                displayCourses(courses, textArea);
            }
            case "3" -> {
                String[] courses = {
                    "Digital, Social Media and Mobile Marketing (MKTG703)",
                    "Marketing for Social Impact (MKTG704)",
                    "2 Chosen Minor Courses",
                    "Co-operative Education - Capstone Experience (BUSS701)"
                };
                displayCourses(courses, textArea);
            }
            default -> textArea.append("Invalid year for Marketing\n");
        }
    }
}
package com.iexceed.marketplacesrv.model;

import java.util.List;

public class ProfessionalDetails {

	private String occupation;
	private EmploymentPeriod employmentPeriod;
	private List<Education> education;
	private List<Certification> certification;
	private String personalWebsite;

	// Getters and Setters

	public static class EmploymentPeriod {
		private String from;
		private String to;

		// Getters and Setters
		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}
	}

	public static class Education {
		private String school;
		private String degree;
		private Period period;

		// Getters and Setters

		public static class Period {
			private String from;
			private String to;

			// Getters and Setters
			public String getFrom() {
				return from;
			}

			public void setFrom(String from) {
				this.from = from;
			}

			public String getTo() {
				return to;
			}

			public void setTo(String to) {
				this.to = to;
			}
		}

		public String getSchool() {
			return school;
		}

		public void setSchool(String school) {
			this.school = school;
		}

		public String getDegree() {
			return degree;
		}

		public void setDegree(String degree) {
			this.degree = degree;
		}

		public Period getPeriod() {
			return period;
		}

		public void setPeriod(Period period) {
			this.period = period;
		}

	}

	public static class Certification {
		private String certificationName;
		private String certificationFrom;
		private CertificationPeriod certificationPeriod;

		// Getters and Setters

		public static class CertificationPeriod {
			private String from;

			// Getters and Setters
			public String getFrom() {
				return from;
			}

			public void setFrom(String from) {
				this.from = from;
			}
		}

		public String getCertificationName() {
			return certificationName;
		}

		public void setCertificationName(String certificationName) {
			this.certificationName = certificationName;
		}

		public String getCertificationFrom() {
			return certificationFrom;
		}

		public void setCertificationFrom(String certificationFrom) {
			this.certificationFrom = certificationFrom;
		}

		public CertificationPeriod getCertificationPeriod() {
			return certificationPeriod;
		}

		public void setCertificationPeriod(CertificationPeriod certificationPeriod) {
			this.certificationPeriod = certificationPeriod;
		}

	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public EmploymentPeriod getEmploymentPeriod() {
		return employmentPeriod;
	}

	public void setEmploymentPeriod(EmploymentPeriod employmentPeriod) {
		this.employmentPeriod = employmentPeriod;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<Certification> getCertification() {
		return certification;
	}

	public void setCertification(List<Certification> certification) {
		this.certification = certification;
	}

	public String getPersonalWebsite() {
		return personalWebsite;
	}

	public void setPersonalWebsite(String personalWebsite) {
		this.personalWebsite = personalWebsite;
	}

}

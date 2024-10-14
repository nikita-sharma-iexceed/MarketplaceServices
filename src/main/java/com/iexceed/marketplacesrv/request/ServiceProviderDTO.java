package com.iexceed.marketplacesrv.request;

import java.util.List;

public class ServiceProviderDTO {

	private BasicDetails basicDetails;
	private ProfessionalDetails professionalDetails;

	public static class BasicDetails {
		private String firstName;
		private String lastName;
		private String email;
		private String phoneNumber;
		private String displayName;
		private String headline;
		private String aboutMe;
		private String accountNo;
		private String location;
		private String profileImg;
		private String address;
		private List<Language> languages;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getHeadline() {
			return headline;
		}

		public void setHeadline(String headline) {
			this.headline = headline;
		}

		public String getAboutMe() {
			return aboutMe;
		}

		public void setAboutMe(String aboutMe) {
			this.aboutMe = aboutMe;
		}

		public String getAccountNo() {
			return accountNo;
		}

		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}

		public List<Language> getLanguages() {
			return languages;
		}

		public void setLanguages(List<Language> languages) {
			this.languages = languages;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getProfileImg() {
			return profileImg;
		}

		public void setProfileImg(String profileImg) {
			this.profileImg = profileImg;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}

	public static class ProfessionalDetails {
		private String occupation;
		private EmploymentPeriod employmentPeriod;
		private List<Education> education;
		private List<Certification> certification;
		private String personalWebsite;

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

	public static class Language {
		private String language;
		private String level;

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

	}

	public static class EmploymentPeriod {
		private String from;
		private String to;

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
		private EmploymentPeriod period;

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

		public EmploymentPeriod getPeriod() {
			return period;
		}

		public void setPeriod(EmploymentPeriod period) {
			this.period = period;
		}

	}

	public static class Certification {
		private String certificationName;
		private String certificationFrom;
		private CertificationPeriod certificationPeriod;

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

	public static class CertificationPeriod {
		private String from;

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

	}

	public BasicDetails getBasicDetails() {
		return basicDetails;
	}

	public void setBasicDetails(BasicDetails basicDetails) {
		this.basicDetails = basicDetails;
	}

	public ProfessionalDetails getProfessionalDetails() {
		return professionalDetails;
	}

	public void setProfessionalDetails(ProfessionalDetails professionalDetails) {
		this.professionalDetails = professionalDetails;
	}

}

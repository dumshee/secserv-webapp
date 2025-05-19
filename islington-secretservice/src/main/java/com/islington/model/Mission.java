package com.islington.model;
import java.sql.Date;
public class Mission {
	    private int missionId;
	    private String missionName;
	    private String missionDescription;
	    private Date startDate;
	    private Date endDate;
	    private String status;
	    
		public Mission() {
			super();
		}
		public int getMissionId() {
			return missionId;
		}
		public void setMissionId(int missionId) {
			this.missionId = missionId;
		}
		public String getMissionName() {
			return missionName;
		}
		public void setMissionName(String missionName) {
			this.missionName = missionName;
		}
		public String getMissionDescription() {
			return missionDescription;
		}
		public void setMissionDescription(String missionDescription) {
			this.missionDescription = missionDescription;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    
}

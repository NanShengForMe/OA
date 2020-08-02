package cn.bagebeyond.oa.activitimanage.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title: LeaveBean</p>
 * <p>Description:请假表单相关Bean </p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2018年3月24日 上午11:02:40
 *
 */
public class LeaveBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 4277515992589522616L;
	
	private Long leaveId;	//请假单id
	private Long leaveUserId;  //请假人员id
	private String leaveUserName;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date leaveDate;
	private Integer leaveDays;
	private String leaveReason;
	private String remark;
	private Integer leaveState;
	private String leaveStateDesc;
	private String processInstanceId;
	
	public Long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}
	public Long getLeaveUserId() {
		return leaveUserId;
	}
	public void setLeaveUserId(Long leaveUserId) {
		this.leaveUserId = leaveUserId;
	}
	public String getLeaveUserName() {
		return leaveUserName;
	}
	public void setLeaveUserName(String leaveUserName) {
		this.leaveUserName = leaveUserName;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Integer getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(Integer leaveDays) {
		this.leaveDays = leaveDays;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getLeaveState() {
		return leaveState;
	}
	public void setLeaveState(Integer leaveState) {
		this.leaveState = leaveState;
	}
	public String getLeaveStateDesc() {
		return leaveStateDesc;
	}
	public void setLeaveStateDesc(String leaveStateDesc) {
		this.leaveStateDesc = leaveStateDesc;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}

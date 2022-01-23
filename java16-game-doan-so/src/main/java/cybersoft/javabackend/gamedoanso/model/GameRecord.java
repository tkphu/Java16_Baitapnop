package cybersoft.javabackend.gamedoanso.model;
/*
 * Mục đích: Lớp quản lý lịch sử chơi game
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.time.LocalDateTime;

import cybersoft.javabackend.gamedoanso.util.DateUtils;

public class GameRecord {
	private int id = 1;
	private Email email;
	private int number;
	private int point;
	private String startDate;
	private String finishDate;
	private boolean isFinished;

	private static int _recordNumbers = 1;

	public GameRecord(Email email) {
		id = _recordNumbers++;
		this.email = email;
		number = (int) (Math.random() * 1000) + 1;
		point = 0;
		startDate = DateUtils.toString(LocalDateTime.now());
		isFinished = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
}

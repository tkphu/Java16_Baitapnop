package cybersoft.javabackend.gamedoanso.service;
/*
 * Mục đích: Lớp lưu trữ thông tin thay cho DB
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.util.ArrayList;
import java.util.List;

import cybersoft.javabackend.gamedoanso.model.Email;
import cybersoft.javabackend.gamedoanso.model.GameRecord;

public class StoreService {
	public static final List<GameRecord> records = new ArrayList<>();
	public static final List<Email> emails = new ArrayList<>();
}

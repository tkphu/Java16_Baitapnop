package cybersoft.javabackend.gamedoanso.service;
/*
 * Mục đích: Lớp xử lý và khởi tạo game
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cybersoft.javabackend.gamedoanso.model.Email;
import cybersoft.javabackend.gamedoanso.model.GameRecord;
import cybersoft.javabackend.gamedoanso.util.DateUtils;

public class GameService {
	private List<GameRecord> records;
	private List<Email> emails;

	public GameService() {
		records = StoreService.records;
		emails = StoreService.emails;
	}

	public GameRecord loadGame(String email, String password) {
		Email loggedEmail = null;

		// check existed user
		for (Email e : emails) {
			if (e.getEmail().equalsIgnoreCase(email)) {
				if (e.getEPassword().equals(password)) {
					loggedEmail = e;
					break;
				}

				return null;
			}
		}

		if (loggedEmail == null) {
			loggedEmail = new Email(email, password);
			emails.add(loggedEmail);
		}

		GameRecord game = null;
		// load existed unfinished game record
		for (GameRecord record : records) {
			if (record.getEmail().getEmail().equals(loggedEmail.getEmail()) && record.getIsFinished() == false) {
				game = record;
				return game;
			}
		}

		game = new GameRecord(loggedEmail);
		records.add(game);

		return game;
	}

	public GameRecord playGame(int recordId, int tryNumber) {
		GameRecord record = null;

		/* old school */
		for (GameRecord r : records) {
			if (r.getId() == recordId && !r.getIsFinished()) {
				record = r;
				break;
			}
		}
		/*
		 * Java 8 stream record = records.stream() .filter(t -> t.getId() == recordId &&
		 * !r.isFinished()) .collect(Collectors.toList()).get(0);
		 */

		if (record == null) {
			return null;
		}

		record.setPoint(record.getPoint() + 1);

		if (record.getNumber() == tryNumber) {
			record.setFinishDate(DateUtils.toString(LocalDateTime.now()));
			record.setIsFinished(true);
		}

		return record;
	}
}

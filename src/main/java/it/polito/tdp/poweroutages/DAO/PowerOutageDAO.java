package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getAllEventi(Nerc n)
	{
		String sql = "SELECT id,customers_affected,date_event_began, date_event_finished, nerc_id "
				+ "FROM poweroutages "
				+ "WHERE nerc_id = ? "
				+ "ORDER BY date_event_began DESC";
		
		List<PowerOutages> eventi = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, n.getId());
			
			ResultSet res = st.executeQuery();
			
			
			

			while (res.next()) {
				PowerOutages p = new PowerOutages(res.getInt("id"), null, res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime(), res.getInt("customers_affected"));
				eventi.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return eventi;
	}
	

}

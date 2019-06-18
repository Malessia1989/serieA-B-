package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.SquadraComuneEpeso;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons(Map<Integer, Season> idMap) {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if (idMap.get(res.getInt("season")) == null) {
					Season s = new Season(res.getInt("season"), res.getString("description"));
					result.add(s);
					idMap.put(s.getSeason(), s);
				} else {
					result.add(idMap.get(res.getInt("season")));
				}
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<SquadraComuneEpeso> getSquadreComuni(Map<Integer, Season> idMap) {
		
		String sql="select m1.Season s1, m2.Season s2, count(distinct m1.HomeTeam) as peso " + 
				"from matches m1, matches m2 " + 
				"where m1.Season > m2.Season " + 
				"and m1.HomeTeam = m2.HomeTeam  " + 
				"group by s1,s2 ";
		
		List<SquadraComuneEpeso> result= new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Season s1= idMap.get(res.getInt("s1"));
				Season s2= idMap.get(res.getInt("s2"));
				double peso=res.getDouble("peso");
				
				if(s1 != null && s2 != null) {
					SquadraComuneEpeso s= new SquadraComuneEpeso(s1, s2, peso);
					result.add(s);
				}
				
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		

	}

}

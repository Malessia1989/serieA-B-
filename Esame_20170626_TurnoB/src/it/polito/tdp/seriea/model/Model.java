package it.polito.tdp.seriea.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	
	SerieADAO dao;
	Graph<Season, DefaultWeightedEdge> grafo;
	Map<Integer, Season> idMap;
	
	public Model() {
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao= new SerieADAO();
		idMap= new HashMap<>();
		dao.listSeasons(idMap);
	}

	public List<Season> getSeason() {		
		return dao.listSeasons(idMap);
	}

	public void creaGrafo() {
		
		Graphs.addAllVertices(grafo, idMap.values());
		
		List<SquadraComuneEpeso> comune= dao.getSquadreComuni(idMap);
		for(SquadraComuneEpeso sq: comune) {
			Graphs.addEdgeWithVertices(grafo, sq.getS1(), sq.getS2(), sq.getPeso());
		}
		
		System.out.println("vertici: "+grafo.vertexSet().size());
		System.out.println("archi: "+grafo.edgeSet().size());
		for(DefaultWeightedEdge edge: grafo.edgeSet()) {
			System.out.println(edge + "peso:" +grafo.getEdgeWeight(edge));			
		}
		
	}

	public Graph<Season, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public String getConnessi(Season sInput) {
		String risultato="";
		List<Season> vicini= Graphs.neighborListOf(grafo,sInput);
		Collections.sort(vicini);
		
		for(Season s: vicini) {
			DefaultWeightedEdge edge=grafo.getEdge(sInput, s);
			risultato+= s.toString()+ " "+ grafo.getEdgeWeight(edge)+"\n";
		}
		
		return risultato;
	}
	
}

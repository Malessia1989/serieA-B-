package it.polito.tdp.seriea.model;

public class SquadraComuneEpeso {
	
	Season s1;
	Season s2;
	double peso;
	public SquadraComuneEpeso(Season s1, Season s2, double peso) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.peso = peso;
	}
	public Season getS1() {
		return s1;
	}
	public void setS1(Season s1) {
		this.s1 = s1;
	}
	public Season getS2() {
		return s2;
	}
	public void setS2(Season s2) {
		this.s2 = s2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s1 == null) ? 0 : s1.hashCode());
		result = prime * result + ((s2 == null) ? 0 : s2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SquadraComuneEpeso other = (SquadraComuneEpeso) obj;
		if (s1 == null) {
			if (other.s1 != null)
				return false;
		} else if (!s1.equals(other.s1))
			return false;
		if (s2 == null) {
			if (other.s2 != null)
				return false;
		} else if (!s2.equals(other.s2))
			return false;
		return true;
	}
	
	

}

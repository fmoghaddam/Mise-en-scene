package model;

public class Rating {
	private String Source;
	private String Value;

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Source == null) ? 0 : Source.hashCode());
		result = prime * result + ((Value == null) ? 0 : Value.hashCode());
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
		Rating other = (Rating) obj;
		if (Source == null) {
			if (other.Source != null)
				return false;
		} else if (!Source.equals(other.Source))
			return false;
		if (Value == null) {
			if (other.Value != null)
				return false;
		} else if (!Value.equals(other.Value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rating [Source=" + Source + ", Value=" + Value + "]";
	}

}

package pl.calendar.model;


public class Plant {
	private long id;
	private String name;
    private String fertilization;
    private String pruning;
    private String spraying;	
    private User user;


    public Plant(){}
    
	public Plant(Plant plant) {

		this.id = plant.id;
		this.name = plant.name;
		this.fertilization = plant.fertilization;
		this.pruning = plant.pruning;
		this.spraying = plant.spraying;
        this.user = new User(plant.user);

	}
	
	
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFertilization() {
		return fertilization;
	}

	public void setFertilization(String fertilization) {
		this.fertilization = fertilization;
	}

	public String getPruning() {
		return pruning;
	}

	public void setPruning(String pruning) {
		this.pruning = pruning;
	}

	public String getSpraying() {
		return spraying;
	}

	public void setSpraying(String spraying) {
		this.spraying = spraying;
	}
	
	public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

	
	
	@Override
	public String toString() {
		return "Plant [id=" + id + ", name=" + name + ", fertilization=" + fertilization + ", pruning="
				+ pruning + ", spraying=" + spraying + ", user=" + user + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fertilization == null) ? 0 : fertilization.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((pruning == null) ? 0 : pruning.hashCode());
		result = prime * result + ((spraying == null) ? 0 : spraying.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Plant other = (Plant) obj;
		if (fertilization == null) {
			if (other.fertilization != null)
				return false;
		} else if (!fertilization.equals(other.fertilization))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id != other.id)
			return false;
		if (pruning == null) {
			if (other.pruning != null)
				return false;
		} else if (!pruning.equals(other.pruning))
			return false;
		if (spraying == null) {
			if (other.spraying != null)
				return false;
		} else if (!spraying.equals(other.spraying))
			return false;
		return true;
		
	}







	
}

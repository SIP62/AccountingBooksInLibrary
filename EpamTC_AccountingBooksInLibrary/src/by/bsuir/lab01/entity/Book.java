package by.bsuir.lab01.entity;

public class Book {
	String author;
	String title;
	int year;
	String issuetype;
	public Book(String author, String title, int year, String issuetype) {
		super();
		this.author = author;
		this.title = title;
		this.year = year;
		this.issuetype = issuetype;
	}
	public Book() {
		
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((issuetype == null) ? 0 : issuetype.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (issuetype == null) {
			if (other.issuetype != null)
				return false;
		} else if (!issuetype.equals(other.issuetype))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	

}

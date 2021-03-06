package vo;

public class RStory {
	private RProject rProject;
	private String rPJT_url;
	private String rPJT_price;
	private String rPJT_thumbnail;
	private String rPJT_story;
	private String rPJT_tag;
	private String rPJT_paper;
	public RStory() {
		super();
	}
	public RStory(RProject rProject, String rPJT_url, String rPJT_price, String rPJT_thumbnail, 
			String rPJT_story, String rPJT_tag, String rPJT_paper) {
		super();
		this.rProject = rProject;
		this.rPJT_url = rPJT_url;
		this.rPJT_price = rPJT_price;
		this.rPJT_thumbnail = rPJT_thumbnail;
		this.rPJT_story = rPJT_story;
		this.rPJT_tag = rPJT_tag;
		this.rPJT_paper = rPJT_paper;
	}
	public RProject getrProject() {
		return rProject;
	}
	public void setrProject(RProject rProject) {
		this.rProject = rProject;
	}
	public String getrPJT_url() {
		return rPJT_url;
	}
	public void setrPJT_url(String rPJT_url) {
		this.rPJT_url = rPJT_url;
	}
	public String getrPJT_price() {
		return rPJT_price;
	}
	public void setrPJT_price(String rPJT_price) {
		this.rPJT_price = rPJT_price;
	}
	public String getrPJT_thumbnail() {
		return rPJT_thumbnail;
	}
	public void setrPJT_thumbnail(String rPJT_thumbnail) {
		this.rPJT_thumbnail = rPJT_thumbnail;
	}
	public String getrPJT_story() {
		return rPJT_story;
	}
	public void setrPJT_story(String rPJT_story) {
		this.rPJT_story = rPJT_story;
	}
	public String getrPJT_tag() {
		return rPJT_tag;
	}
	public void setrPJT_tag(String rPJT_tag) {
		this.rPJT_tag = rPJT_tag;
	}
	public String getrPJT_paper() {
		return rPJT_paper;
	}
	public void setrPJT_paper(String rPJT_paper) {
		this.rPJT_paper = rPJT_paper;
	}
	
	
}

package cn.ayit.domain.data;

public class ComboBox {

	private String value;
	private String text;
	private String desc;
	private boolean selected=false;
	
	public ComboBox() {
	}

	public ComboBox(String value, String text, String desc) {
		this.value = value;
		this.text = text;
		this.desc = desc;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "ComboBox [value=" + value + ", text=" + text + ", desc=" + desc
				+ ", selected=" + selected + "]";
	}
	
	
}

public class Item {
    private String label;
    private String description;

    public Item(String _label, String _description)
    {
        this.label = _label;
        this.description = _description;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString()
    {
        if (this.description.equals(""))
        {
            return String.format("%s", this.label);
        }
        else if (this.label.equals(""))
        {
            return String.format("%s", this.description);
        }
        else {
            return String.format("%s - %s", this.label, this.description);
        }
    }
}

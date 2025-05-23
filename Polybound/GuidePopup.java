/*
 * Noah Guan
 */

public class GuidePopup extends Popup
{
	public GuidePopup()
	{
		super("Guide");
		
	}
	/*
	 * This method implements the parent class. It gets the previous popup
	 * and replaces the actual content in the center. 
	 */
    public JPanel getContentPanel()
	{
		JPanel toReturn = super.getContentPanel();
		contentPanel = new UpgradePopupContent();
		
		//add popup content to panel's center 
		toReturn.add(contentPanel, BorderLayout.CENTER);

		return toReturn;
	}
}

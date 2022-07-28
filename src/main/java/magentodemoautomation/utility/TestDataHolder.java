package magentodemoautomation.utility;

public class TestDataHolder {
    private String customerGroupName;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmailAddress;

    public TestDataHolder() {
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public TestDataHolder(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

}

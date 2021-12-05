package resources;

public class ResourceServer {
    private TestResource resource;

    public ResourceServer() {
        this.resource = new TestResource();
    }

    public void setResource(TestResource resource) {
        this.resource = resource;
    }

    public int getAge() {
        return resource.getAge();
    }

    public String getName() {
        return resource.getName();
    }
}

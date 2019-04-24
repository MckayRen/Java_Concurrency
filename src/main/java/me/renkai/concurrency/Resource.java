package me.renkai.concurrency;

public class Resource {
    private static class ResourceHolder {
        private static Resource resource = new Resource();
    }

    public Resource getResource() {
        return ResourceHolder.resource;
    }
}

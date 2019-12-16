package ohdm.storage;

public interface ClassificationInterface {
    
    public boolean checkIfClassificationIdExists(String classification);
    public void addClassification(String classification, String subclassname);
}

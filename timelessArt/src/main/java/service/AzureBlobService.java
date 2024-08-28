package service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class AzureBlobService {

    private final BlobServiceClient blobServiceClient;

    // Injectează valorile din application.properties
    @ConfigProperty(name = "azure.storage.account-name")
    String accountName;

    @ConfigProperty(name = "azure.storage.account-key")
    String accountKey;

    public AzureBlobService() {
        // Inițializează BlobServiceClient folosind account name și key
        String endpoint = "https://" + accountName + ".blob.core.windows.net";
        this.blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new com.azure.storage.common.StorageSharedKeyCredential(accountName, accountKey))
                .buildClient();
    }

    // Exemplar de metodă pentru upload de fișiere în Blob Storage
    public void uploadBlob(String containerName, String blobName, byte[] data) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        // Asigură-te că containerul există (crează-l dacă nu există)
        if (!containerClient.exists()) {
            containerClient.create();
        }

        // Upload blob
        containerClient.getBlobClient(blobName).upload(new java.io.ByteArrayInputStream(data), data.length, true);

        // Opțional: Setează tipul de conținut
        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType("image/jpeg");
        containerClient.getBlobClient(blobName).setHttpHeaders(headers);
    }

    // Exemplar de metodă pentru generarea unui SAS token pentru acces temporar
    public String generateBlobSasUrl(String containerName, String blobName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        // Verifică dacă blob-ul există
        if (!containerClient.getBlobClient(blobName).exists()) {
            throw new RuntimeException("Blob not found!");
        }

        // Generează URL-ul cu SAS Token
        // (exemplu simplificat, trebuie implementat conform cerințelor de securitate)
        return containerClient.getBlobClient(blobName).getBlobUrl();
    }
}

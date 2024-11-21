/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.wolfeindustries.ldapgetuniqueobjects;

import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldif.LDIFException;
import com.unboundid.ldif.LDIFReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Brian
 */
public class LdapGetUniqueObjectclassSets {

    public static void main(String[] args) {
        String pathToLDIFFile = args[0];
        LDIFReader ldifReader = null;
        HashMap<List,String> uniqueObjectTypes = new HashMap<>();
        try {
            ldifReader = new LDIFReader(pathToLDIFFile);
        } catch (IOException ex) {
            System.out.printf("ERROR: Unable to read LDIF file");
        }

        int entriesRead = 0;
        int errorsEncountered = 0;
        while (true){
            Entry entry;
            try{
                entry = ldifReader.readEntry();
                if (entry == null){
                // All entries have been read.
                break;
                }
                
                System.out.printf("Entry: %s\n", entry.toLDIFString());
                uniqueObjectTypes.put(Arrays.asList(entry.getAttributeValues("objectclass")), entry.getDN());
                
                entriesRead++;
            }
            catch (LDIFException le){
                errorsEncountered++;
                if (le.mayContinueReading()){
                    // A recoverable error occurred while attempting to read a change
                    // record, at or near line number le.getLineNumber()
                    // The entry will be skipped, but we'll try to keep reading from the
                    // LDIF file.
                    continue;
                }
                else{
                    // An unrecoverable error occurred while attempting to read an entry
                    // at or near line number le.getLineNumber()
                    // No further LDIF processing will be performed.
                    break;
                }
            }
            catch (IOException ioe)
            {
                // An I/O error occurred while attempting to read from the LDIF file.
                // No further LDIF processing will be performed.
                errorsEncountered++;
                break;
            }
        }
        try {
            ldifReader.close();
        } catch (IOException ex) {
            System.out.printf("ERROR: Unable to close file");
        }
        for (Map.Entry e : uniqueObjectTypes.entrySet()){
            System.out.printf("dn: %s\n", e.getValue());
            System.out.printf("objectclasses: %s\n", e.getKey());
        }
        System.out.printf("Number of Unique Objectclass Sets: %s\n", uniqueObjectTypes.keySet().size());
        System.out.printf("Number of Entries Processed: %d\n", entriesRead);
    }
}

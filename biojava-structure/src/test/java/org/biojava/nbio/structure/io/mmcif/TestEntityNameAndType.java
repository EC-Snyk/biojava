package org.biojava.nbio.structure.io.mmcif;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;

import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.Compound;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.align.util.AtomCache;
import org.biojava.nbio.structure.io.FileParsingParameters;
import org.junit.Test;
/**
 * Test to ensure the entity name and type
 * @author Anthony Bradley
 *
 */
public class TestEntityNameAndType {

	@Test
	/**
	 * 
	 */
	public void testEntityId() throws IOException, StructureException {
		
		// Set up the atom cache to parse on Internal chain id
		AtomCache cache = new AtomCache();
		cache.setUseMmCif(true);
		FileParsingParameters params = cache.getFileParsingParams();
		params.setUseInternalChainId(true);
		DownloadChemCompProvider cc = new DownloadChemCompProvider();
		ChemCompGroupFactory.setChemCompProvider(cc);
		cc.checkDoFirstInstall();
		cache.setFileParsingParams(params);
		StructureIO.setAtomCache(cache);
		// This is hte information we want to test against
		String[] typeInformation = new String[] {"polymer", "non-polymer", "non-polymer", "non-polymer", "non-polymer", "water"};
		String[] descriptionInformation = new String[] {"BROMODOMAIN ADJACENT TO ZINC FINGER DOMAIN PROTEIN 2B","4-FLUOROBENZAMIDOXIME", "4-FLUOROBENZAMIDOXIME", "METHANOL", "METHANOL", "water"};	
		
		
		/// TODO GET ALL THE ENTITY INFORMATION REQUIRED FOR 4CUP 
		// Get this structure
		Structure bioJavaStruct = StructureIO.getStructure("4cup");
		String[] testTypeInfo = new String[6];
		String[] testDescInfo = new String[6];
		// Now loop through the structure
		int chainCounter = 0;
		for (Chain c: bioJavaStruct.getChains()) {
			// Now get the entity information we want to test
			Compound thisCmpd = c.getCompound();
//			testTypeInfo[chainCounter] = thisCmpd.get
			testDescInfo[chainCounter] = thisCmpd.getMolName();
			chainCounter++;
		}
		// Now check they're both the same
		assertArrayEquals(testDescInfo, descriptionInformation);
//		assertArrayEquals(testTypeInfo, typeInformation);

		
		
	}
}

package znu.practice.ie.with.webpack.hwinfo.log.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
public class CsvDataLoaderTest {

  @Autowired
  CsvDataLoader loader;

  @Test
  public void load() throws FileNotFoundException, IOException {

    File file = new File("src/test/resources/mock/data_sample.CSV");
    MockMultipartFile multipartFile = new MockMultipartFile("data_sample.CSV", new FileInputStream(file));
    List<String[]> list = loader.loadManyToManyRelationship(multipartFile);

    assertNotNull(list);
    assertTrue(list.size() > 0);
    assertTrue(list.get(0).length > 0);

    StringBuilder headerBuilder = new StringBuilder();
    for (String header : list.get(0)) {
      headerBuilder.append(header);
      headerBuilder.append(",");
    }
    headerBuilder.deleteCharAt(headerBuilder.length() - 1);
    String headers = headerBuilder.toString();
    System.out.println(headers);
    String headersOrigin = "Date,Time,Virtual Memory Commited [MB],Virtual Memory Available [MB],Virtual Memory Load [%],Physical Memory Used [MB],Physical Memory Available [MB],Physical Memory Load [%],Page File Usage [%],Core 0 VID [V],Core 1 VID [V],Core 2 VID [V],Core 3 VID [V],Core 4 VID [V],Core 5 VID [V],Core 6 VID [V],Core 7 VID [V],Core 0 Clock (perf #3/8) [MHz],Core 1 Clock (perf #1/2) [MHz],Core 2 Clock (perf #2/6) [MHz],Core 3 Clock (perf #1/4) [MHz],Core 4 Clock (perf #4/1) [MHz],Core 5 Clock (perf #5/3) [MHz],Core 6 Clock (perf #6/5) [MHz],Core 7 Clock (perf #7/7) [MHz],Bus Clock [MHz],Core 0 T0 Effective Clock [MHz],Core 0 T1 Effective Clock [MHz],Core 1 T0 Effective Clock [MHz],Core 1 T1 Effective Clock [MHz],Core 2 T0 Effective Clock [MHz],Core 2 T1 Effective Clock [MHz],Core 3 T0 Effective Clock [MHz],Core 3 T1 Effective Clock [MHz],Core 4 T0 Effective Clock [MHz],Core 4 T1 Effective Clock [MHz],Core 5 T0 Effective Clock [MHz],Core 5 T1 Effective Clock [MHz],Core 6 T0 Effective Clock [MHz],Core 6 T1 Effective Clock [MHz],Core 7 T0 Effective Clock [MHz],Core 7 T1 Effective Clock [MHz],Average Effective Clock [MHz],Core 0 T0 Usage [%],Core 0 T1 Usage [%],Core 1 T0 Usage [%],Core 1 T1 Usage [%],Core 2 T0 Usage [%],Core 2 T1 Usage [%],Core 3 T0 Usage [%],Core 3 T1 Usage [%],Core 4 T0 Usage [%],Core 4 T1 Usage [%],Core 5 T0 Usage [%],Core 5 T1 Usage [%],Core 6 T0 Usage [%],Core 6 T1 Usage [%],Core 7 T0 Usage [%],Core 7 T1 Usage [%],Max CPU/Thread Usage [%],Total CPU Usage [%],Core 0 Ratio [x],Core 1 Ratio [x],Core 2 Ratio [x],Core 3 Ratio [x],Core 4 Ratio [x],Core 5 Ratio [x],Core 6 Ratio [x],Core 7 Ratio [x],Memory Clock [MHz],Memory Clock Ratio [x],Tcas [T],Trcd [T],Trp [T],Tras [T],Trc [T],Trfc [T],Command Rate [T],CPU (Tctl/Tdie) [°C],CPU Die (average) [°C],CPU CCD1 (Tdie) [°C],CPU Core Voltage (SVI2 TFN) [V],SoC Voltage (SVI2 TFN) [V],SoC Current (SVI2 TFN) [A],CPU TDC [A],CPU EDC [A],CPU Package Power (SMU) [W],Core 0 Power (SMU) [W],Core 1 Power (SMU) [W],Core 2 Power (SMU) [W],Core 3 Power (SMU) [W],Core 4 Power (SMU) [W],Core 5 Power (SMU) [W],Core 6 Power (SMU) [W],Core 7 Power (SMU) [W],CPU Core Power [W],CPU SoC Power (SVI2 TFN) [W],Core+SoC Power [W],CPU PPT [W],Infinity Fabric Clock (FCLK) [MHz],Memory Controller Clock (UCLK) [MHz],CPU PPT Limit [%],CPU TDC Limit [%],CPU EDC Limit [%],Thermal Throttling (HTC) [Yes/No],Thermal Throttling (PROCHOT CPU) [Yes/No],Thermal Throttling (PROCHOT EXT) [Yes/No],Motherboard [°C],External 1 [°C],System [°C],CPU [°C],VR MOS [°C],Chipset [°C],CPU (PECI) [°C],Vcore [V],+5V [V],AVCC [V],3VCC [V],+12V [V],VIN4 [V],3VSB [V],VTT [V],VIN6 [V],CPU NB/SoC [V],DIMM [V],VIN7 [V],CPU [RPM],System 1 [RPM],System 2 [RPM],System 3 [RPM],System 4 [RPM],Chassis Intrusion [Yes/No],Chipset [°C],VR Loop1 [°C],VR Loop2 [°C],VR VOUT [V],VR VIN [V],Current (IOUT) [A],Current (IIN) [A],Power (POUT) [W],Power (Input) [W],VR Loop1 [°C],VR Loop2 [°C],VR VOUT [V],VR VIN [V],Current (IOUT) [A],Current (IIN) [A],Power (POUT) [W],Power (Input) [W],Drive Airflow Temperature [°C],Drive Remaining Life [%],Drive Failure [Yes/No],Drive Warning [Yes/No],Total Host Writes [GB],Total Host Reads [GB],Drive Temperature [°C],Drive Airflow Temperature [°C],Drive Failure [Yes/No],Drive Warning [Yes/No],Total Host Writes [GB],Total Host Reads [GB],Drive Temperature [°C],Drive Temperature 2 [°C],Drive Remaining Life [%],Drive Failure [Yes/No],Drive Warning [Yes/No],Total Host Writes [GB],Total Host Reads [GB],Read Activity [%],Write Activity [%],Total Activity [%],Read Rate [MB/s],Write Rate [MB/s],Read Total [MB],Write Total [MB],Read Activity [%],Write Activity [%],Total Activity [%],Read Rate [MB/s],Write Rate [MB/s],Read Total [MB],Write Total [MB],Read Activity [%],Write Activity [%],Total Activity [%],Read Rate [MB/s],Write Rate [MB/s],Read Total [MB],Write Total [MB],GPU Temperature [°C],GPU Memory Junction Temperature [°C],GPU VR VDDC Temperature [°C],GPU Hot Spot Temperature [°C],GPU VR MVDD0 Temperature [°C],GPU VR MVDD1 Temperature [°C],GPU Core Voltage (VDDC) [V],GPU Memory Voltage (MVDDC) [V],GPU Fan [RPM],GPU Core Current [A],GPU Memory Current [A],GPU Core Power [W],GPU Memory Power [W],GPU ASIC Power [W],GPU Clock [MHz],GPU Memory Clock [MHz],GPU SoC Clock [MHz],GPU UVD1 Clock [MHz],GPU UVD2 Clock [MHz],GPU Utilization [%],GPU D3D Usage [%],GPU Memory Controller Utilization [%],GPU Video Decode 0 Usage [%],GPU Video Encode 0 Usage [%],GPU Computing (High Priority Compute) Usage [%],GPU Computing (Compute 3) Usage [%],GPU Computing (Compute 0) Usage [%],GPU Computing (Compute 1) Usage [%],GPU Fan Speed [%],GPU D3D Memory Dedicated [MB],GPU D3D Memory Dynamic [MB],PCIe Link Speed [GT/s],GPU Memory Usage [MB],Total DL [MB],Total UP [MB],Current DL rate [KB/s],Current UP rate [KB/s],Total DL [MB],Total UP [MB],Current DL rate [KB/s],Current UP rate [KB/s],Total Errors []";
    assertEquals(headersOrigin, headers);

    StringBuilder dataBuilder = new StringBuilder();
    for (String data : list.get(1)) {
      dataBuilder.append(data);
      dataBuilder.append(",");
    }
    dataBuilder.deleteCharAt(dataBuilder.length() - 1);
    String datas = dataBuilder.toString();
    System.out.println(datas);
    String dataOrigin = "28.1.2020,40:21.2,8791,17271,33.7,4770,11564,29.2,0,1.463,1.425,1.431,1.425,1.456,1.431,1.469,1.463,4400,4400,4400,4400,4425,4450,4425,4425,100,329.6,2.1,490.2,411.1,378.7,3.4,342.7,768.7,132.2,1,63.8,3.5,101.5,2.4,59,76,197.9,2.8,0,7.9,2.1,2.8,0,3.5,8.6,1.4,0,0,0,0.7,0,2.8,0,8.6,2,44,44,44,44,44.3,44.5,44.3,44.3,2000,20,20,19,19,39,93,700,1,40.1,37.2,42,1.462,1.087,13.02,10.901,36.748,42.623,1.756,2.692,1.881,2.975,1.411,1.292,1.303,1.341,15.694,14.159,29.805,41.604,1800,1000,29.3,11.5,26.2,No,No,No,37,34.6,34,40.1,42,52,40,1.448,5,3.344,3.312,12.096,0.704,3.344,1.8,0.576,1.12,1.472,1.528,766,1052,475,945,961,No,62.6,44,49,1.426,12.219,16.25,2.688,12,13.5,49,44,1.084,12.219,12.5,1.375,12.5,17,29,99,No,No,566,108,30,30,No,No,257,260,47,56,100,No,No,2920,2777,0,0,0,0,0.041,6,0,0,0,0,0,0,0,0,0.2,0.1,0.3,0.36,0.597,3689,341,53,56,46,53,47,47,1.2,0.85,0,12,3.5,14.4,2.975,26,46,1750,215,105,102,0,3.7,0,0,3.3,0,0,0,0,0,619,127,16,568,6,1,0.035,0.51,0,0,0,0,0";
    assertEquals(dataOrigin, datas);
  }
}

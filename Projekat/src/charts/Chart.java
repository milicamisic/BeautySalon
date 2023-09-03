package charts;

import java.awt.Color;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import otherEntities.ServiceType;
import paket1.BeautySalon;
import service.ManagerService;



public class Chart {
	
	
	public static XYChart getServiceTypeChart() {
		
		BeautySalon beautySalon = BeautySalon.getBeautySalon();
		ManagerService managerService = new ManagerService();
		
		XYChart chart;
		
		chart = new XYChartBuilder().width(700).height(420).title("Service Type Chart").build();
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
	    chart.getStyler().setAxisTitlesVisible(false);
	    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
	    chart.getStyler().setDecimalPattern("0.0");
	    
	    List<Date> xData = new ArrayList<>();
        List<Double> yData;
	    
	    Calendar calendar = Calendar.getInstance();
        for (int i = 1; i <= 12; i++) {
            xData.add(calendar.getTime());
            calendar.add(Calendar.MONTH, -1);
        }
        xData.sort(Comparator.naturalOrder());

        YearMonth yearMonth;
        LocalDate startDate, endDate;
        
        for(ServiceType serviceType : beautySalon.getServiceTypes()) {
        	yData = new ArrayList<>();

            for (Date d : xData) {
            	LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                yearMonth = YearMonth.of(date.getYear(), date.getMonth());
                startDate = yearMonth.atDay(1);
                endDate = yearMonth.atEndOfMonth();
                yData.add(managerService.getServiceTypeChartReport(startDate, endDate, serviceType.getType()));
            }       
            chart.addSeries(serviceType.getType(), xData, yData);
        }
        
    	yData = new ArrayList<>();
    	
    	 for (Date d : xData) {
         	LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             yearMonth = YearMonth.of(date.getYear(), date.getMonth());
             startDate = yearMonth.atDay( 1 );
             endDate = yearMonth.atEndOfMonth();
             yData.add(managerService.getAllServiceTypesChartReport(startDate, endDate));
         }   
        chart.addSeries("Total", xData,yData);
      
		return chart;
	}

	
	public static PieChart getBeauticianChart() {
		
		PieChart chart = new PieChartBuilder().width(520).height(400).title("Beautician Chart").build();
		Color[] colors = new Color[] { Color.RED, Color.BLUE, Color.CYAN, Color.GRAY, Color.PINK, Color.BLACK, Color.GREEN, Color.YELLOW, Color.ORANGE};
	    chart.getStyler().setSeriesColors(colors);
	    
	    ManagerService managerService = new ManagerService();
	    HashMap<String, Integer> beauticianScore = managerService.getBeauticianChart();
	    
	    for (Entry<String, Integer> entry : beauticianScore.entrySet()) {
		    String key = entry.getKey();
		    Number value = entry.getValue();
		    chart.addSeries(key, value);
		    
		}

		return chart;
	}
	
	public static PieChart getAppointmentStatusChart() {
		
		PieChart chart = new PieChartBuilder().width(520).height(400).title("Appointment Status Chart").build();
		Color[] colors = new Color[] { Color.RED, Color.BLUE, Color.CYAN, Color.GRAY, Color.PINK};
	    chart.getStyler().setSeriesColors(colors);
	    
	    ManagerService managerService = new ManagerService();
	    HashMap<String, Integer> appointmentStatusScore = managerService.getAppointmentStatusChart();

	    for (Entry<String, Integer> entry : appointmentStatusScore.entrySet()) {
		    String key = entry.getKey().toString();
		    Number value = entry.getValue();
		    chart.addSeries(key, value);
		    
		}

		return chart;
	}
}

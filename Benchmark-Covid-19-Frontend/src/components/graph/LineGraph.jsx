import ApexChart from 'react-apexcharts'

//Componente de gráfico do tipo Linha
export default function LineGraph({graphName,graphSerieName1,graphData1,graphSerieName2,graphData2}){
    
    const options = {
        chart: {
        type: 'line',
        zoom: {
          enabled: false
        }
      },
      stroke: {
        curve: 'straight'
      },
      title: {
        text: graphName,
        align: 'center',
        style: {
            fontSize:  '36px',
            fontWeight:  'bold',
            color:  '#263238'
          }
      },
      grid: {
        row: {
          colors: ['#f3f3f3', 'transparent'], 
          opacity: 0.5
        },
      },
      yaxis: {
        logarithmic: true
      }
      }

    const series = [{
                        name: graphSerieName1,
                        data: graphData1
                    },
                    {
                        name: graphSerieName2,
                        data: graphData2
                    }]

    return <ApexChart options={options} series={series}/>
}
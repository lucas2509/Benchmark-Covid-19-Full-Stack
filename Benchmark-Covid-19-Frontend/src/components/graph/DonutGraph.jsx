import Chart from 'react-apexcharts';

//Componente de gráfico do tipo Donut
export default function DonutGraph({title,series,label}){
    var options = {
        chart: {
        type: 'donut',
        height: 30,
        },
        responsive: [{
            breakpoint: 480,
            options: {
            chart: {
                width: 20
            },
            legend: {
                position: 'bottom'
            }
            }
        }],
        labels: label,
        title: {
            text: title,
            align: 'center',
            style: {
                fontSize:  '36px',
                fontWeight:  'bold',
                color:  '#263238'
              }
          }
      }

    return <Chart options={options} series={series} type={"donut"}/>;
}
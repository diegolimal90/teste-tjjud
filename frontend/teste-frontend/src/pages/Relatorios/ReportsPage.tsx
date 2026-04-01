import React, { useState } from 'react';
import Layout from '../../components/Layout';
import { dataService } from '../../services/dataService';
import { FileText, Printer, Download, Loader2 } from 'lucide-react';

const ReportsPage: React.FC = () => {
  const [pdfUrl, setPdfUrl] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const handleGenerateReport = async () => {
    setLoading(true);
    try {
      const response = await dataService.gerarRelatorioPdf();
      const file = new Blob([response.data], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(file);
      setPdfUrl(fileURL);
    } catch (error) {
      alert('Erro ao gerar relatório. Verifique se o backend está ativo.');
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const handleDownload = () => {
    if (pdfUrl) {
      const link = document.createElement('a');
      link.href = pdfUrl;
      link.setAttribute('download', 'relatorio_livros.pdf');
      document.body.appendChild(link);
      link.click();
      link.remove();
    }
  };

  return (
    <Layout>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2><FileText className="me-2 text-success" /> Relatórios</h2>
        <button 
          className="btn btn-success d-flex align-items-center px-4" 
          onClick={handleGenerateReport}
          disabled={loading}
        >
          {loading ? <Loader2 className="me-2 animate-spin" size={18} /> : <Printer size={18} className="me-2" />}
          {loading ? 'Gerando...' : 'Gerar Relatório de Livros'}
        </button>
      </div>

      <div className="row">
        <div className="col-12">
          {pdfUrl ? (
            <div className="card bg-dark border-secondary shadow">
              <div className="card-header border-secondary d-flex justify-content-between align-items-center">
                <span className="text-secondary small">RELATORIO_LIVROS.PDF</span>
                <button className="btn btn-sm btn-outline-info" onClick={handleDownload}>
                  <Download size={16} className="me-2" /> Download
                </button>
              </div>
              <div className="card-body p-0" style={{ height: '75vh' }}>
                <iframe 
                  src={pdfUrl} 
                  title="Relatório de Livros" 
                  width="100%" 
                  height="100%" 
                  style={{ border: 'none' }}
                />
              </div>
            </div>
          ) : (
            <div className="card bg-dark border-secondary border-dashed p-5 text-center shadow-sm">
              <div className="p-5">
                <FileText size={64} className="text-secondary opacity-25 mb-3" />
                <h4 className="text-secondary">Nenhum relatório gerado</h4>
                <p className="text-muted">Clique no botão acima para compilar os dados e gerar o PDF via Jasper Reports.</p>
              </div>
            </div>
          )}
        </div>
      </div>
    </Layout>
  );
};

export default ReportsPage;
